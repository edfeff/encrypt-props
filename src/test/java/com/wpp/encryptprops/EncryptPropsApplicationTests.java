package com.wpp.encryptprops;

import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EncryptPropsApplicationTests {

    @Test
    void testBasicEncrypt() {
        //基本加密器 算法 PBEWithMD5AndDES
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword("chupacabras");
        String encryptRoot = basicTextEncryptor.encrypt("root");
        String dencryptRoot = basicTextEncryptor.decrypt(encryptRoot);
        System.out.println(String.format("encrypt=%s dencrypt=%s\n", encryptRoot, dencryptRoot));
        //每次加密都一样
        //encrypt=ANoKfjJL8lESugJFMzlrww== dencrypt=root
        //encrypt=gl2QpMAPW7WnyATVx3THdw== dencrypt=root
    }

    @Test
    void strongBasicEncrypt() {
        //基本加密器 算法 PBEWithMD5AndTripleDES   3Des
        StrongTextEncryptor encryptor = new StrongTextEncryptor();
        encryptor.setPassword("chupacabras");

        System.out.println(encryptor.encrypt("jdbc:mysql://localhost:3306/student"));
        System.out.println(encryptor.encrypt("root"));
        System.out.println(encryptor.encrypt("123456"));
        //        RmFOlQ27iWuCTorDWqE4CMojcEPz0oEpqv4VvhywWYM315MiqhStNfd7bxoLf2Iw
        //EHVTAOgC7Ed+MVdg545uIg==
        //zRGXHf8oKY80ReuE+/yKTg==
    }

}
