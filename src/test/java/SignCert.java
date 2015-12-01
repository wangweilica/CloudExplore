

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Date;

import sun.security.x509.AlgorithmId;
import sun.security.x509.CertificateAlgorithmId;
import sun.security.x509.CertificateIssuerName;
import sun.security.x509.CertificateSerialNumber;
import sun.security.x509.CertificateValidity;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;

public class SignCert {
	
	// 签发者
	public static final String clientStore = "D:\\Credential\\client.jks";
	public static final String clientCertAlias = "client";
	
	// 待签发者
	public static final String serverCert = "D:\\Credential\\server.cer";
	
	// 新的密钥库
	public static final String newServerStore = "D:\\Credential\\newserver.jks";
	public static final String newServerCertAlias = "signedserver";
	
	// 密码
	public static final String password = "changeit";
	
	public static void main(String[] args) throws Exception{
		//签发者信息
		FileInputStream fis = new FileInputStream(clientStore);
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(fis, password.toCharArray());
		Certificate c1 = ks.getCertificate(clientCertAlias);
		PrivateKey caprk = (PrivateKey) ks.getKey(clientCertAlias, password.toCharArray());//证书私钥
		fis.close();
		byte[] encode1 = c1.getEncoded();
		X509CertImpl cimpl = new X509CertImpl(encode1);
		X509CertInfo cinfol = (X509CertInfo) cimpl.get(X509CertImpl.NAME+"."+X509CertImpl.INFO);
		X500Name issuer = (X500Name) cinfol.get(X509CertInfo.SUBJECT+"."+CertificateIssuerName.DN_NAME);
		
		//待签发者
		FileInputStream fis2 = new FileInputStream(serverCert);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		Certificate c2 = cf.generateCertificate(fis2);
		fis2.close();
		byte[] encode2 = c2.getEncoded();
		X509CertImpl cimp2 = new X509CertImpl(encode2);
		X509CertInfo cinfo2 = (X509CertInfo) cimp2.get(X509CertImpl.NAME+"."+X509CertImpl.INFO);
		Date begindate = new Date();//设置有效期
		Date enddate = new Date(begindate.getTime()+3000*24*60*60*1000L);
		CertificateValidity cv = new CertificateValidity(begindate,enddate);
		cinfo2.set(X509CertInfo.VALIDITY, cv);
		int sn = (int) (begindate.getTime()/1000);//设置证书的序列号
		CertificateSerialNumber csn = new CertificateSerialNumber(sn);
		cinfo2.set(X509CertInfo.SERIAL_NUMBER, csn);
		cinfo2.set(X509CertInfo.ISSUER+"."+CertificateIssuerName.DN_NAME, issuer);//设置证书的签发者是谁
		AlgorithmId algorithm = new AlgorithmId(AlgorithmId.md5WithRSAEncryption_oid);//设置证书的算法
		cinfo2.set(CertificateAlgorithmId.NAME+"."+CertificateAlgorithmId.ALGORITHM, algorithm);
		X509CertImpl newcert = new X509CertImpl(cinfo2);//创建一个证书并使用签发者的私钥对待签发者进行签名
		newcert.sign(caprk, "MD5WithRSA");
		System.out.println(newcert);
		ks.setCertificateEntry(newServerCertAlias, newcert);//将新条目存入到密钥库中
		FileOutputStream fos = new FileOutputStream(newServerStore);//再把密钥库输出到一个新的密钥库中
		ks.store(fos, password.toCharArray());// 新密码
		fos.close();
	}
}
