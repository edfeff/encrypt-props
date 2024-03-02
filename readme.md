# springboot3加密配置文件

参考链接

1. http://www.jasypt.org/
2. https://github.com/ulisesbocchio/jasypt-spring-boot
3. https://blog.csdn.net/simonchi/article/details/113796576
4. https://baomidou.com/pages/bab2db/#release

## 步骤

1. 引入依赖
2. 加密需要的内容
3. 写入到配置文件
4. 配置密码启动

### 1.引入依赖

```xml

<dependency>
    <groupId>com.github.ulisesbocchio</groupId>
    <artifactId>jasypt-spring-boot-starter</artifactId>
    <version>3.0.5</version>
</dependency>
```

### 2.加密需要的内容

使用测试类调用加密方法

```java

@SpringBootTest
class EncryptPropsApplicationTests {
    @Test
    void strongBasicEncrypt() {
        //算法 PBEWithMD5AndTripleDES   3Des
        StrongTextEncryptor encryptor = new StrongTextEncryptor();
        encryptor.setPassword("chupacabras");
        System.out.println(encryptor.encrypt("jdbc:mysql://localhost:3306/student"));
        System.out.println(encryptor.encrypt("root"));
        System.out.println(encryptor.encrypt("123456"));
        //RmFOlQ27iWuCTorDWqE4CMojcEPz0oEpqv4VvhywWYM315MiqhStNfd7bxoLf2Iw
        //EHVTAOgC7Ed+MVdg545uIg==
        //zRGXHf8oKY80ReuE+/yKTg==
    }
}

```

### 3.写入到配置文件

```properties
spring.datasource.url=ENC(RmFOlQ27iWuCTorDWqE4CMojcEPz0oEpqv4VvhywWYM315MiqhStNfd7bxoLf2Iw)
spring.datasource.username=ENC(EHVTAOgC7Ed+MVdg545uIg==)
spring.datasource.password=ENC(zRGXHf8oKY80ReuE+/yKTg==)
```

### 4.配置密码启动

这部分可以使用多种方式

1. 直接写入到配置文件（不推荐，仅测试使用）
2. 使用环境变量
3. 使用启动参数

#### 4.1.直接写入到配置文件

在`application.properties`中直接写入如下

```properties
#密码需要抽取到环境变量中，或启动参数中，不能写在这里，
jasypt.encryptor.password=chupacabras
jasypt.encryptor.algorithm=PBEWithMD5AndTripleDES
# 使用的jasypt版本大于3时，需要下面一行配置，使用的版本为2时，不需要下面的配置
jasypt.encryptor.iv-generator-classname=org.jasypt.iv.NoIvGenerator
```

#### 4.2.使用环境变量

设置环境变量

- linux 环境

```bash
export JASYPT_ENCRYPTOR_PASSWORD=chupacabras
export JASYPT_ENCRYPTOR_ALGORITHM=PBEWithMD5AndTripleDES
export JASYPT_ENCRYPTOR_IV-GENERATOR-CLASSNAME=org.jasypt.iv.NoIvGenerator
```

- window环境

```cmd
set JASYPT_ENCRYPTOR_PASSWORD=chupacabras
set JASYPT_ENCRYPTOR_ALGORITHM=PBEWithMD5AndTripleDES
set JASYPT_ENCRYPTOR_IV-GENERATOR-CLASSNAME=org.jasypt.iv.NoIvGenerator
```

#### 4.3.使用启动参数

springboot启动参数

```properties

--jasypt.encryptor.password=chupacabras \
--jasypt.encryptor.algorithm=PBEWithMD5AndTripleDES \
--jasypt.encryptor.iv-generator-classname=org.jasypt.iv.NoIvGenerator
```
