package com.springboot;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MybatisPlusGeneratorTest {
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;

    @Test
    void generator() {
//        List<String> tables = Arrays.asList("hollow_forum_topic", "hollow_forum_message", "hollow_forum_reply");
        List<String> tables = Arrays.asList("hollow_movie_date");
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> builder.author("loki")               //作者
                        .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")    //输出路径(写到java目录)
                        // .enableSwagger()           //开启swagger
                        .dateType(DateType.ONLY_DATE)
                        .commentDate("yyyy-MM-dd"))
                .packageConfig(builder -> builder.parent("com.springboot.buss")
                        .entity("entity")
                        .service("service")
                        .serviceImpl("service")
                        .controller("controller")
                        .mapper("mapper")
                        // .pathInfo(Collections.singletonMap(OutputFile.xml,System.getProperty("user.dir")+"\\src\\main\\resources\\mapper") //重定义输出mapper.xml位置
                        .xml("mapper"))
                .strategyConfig(builder -> builder.addInclude(tables)
                        .addTablePrefix("p_")
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sService")
                        //.formatServiceImplFileName("%sServiceImpl")
                        .entityBuilder()
                        .enableLombok()
                        .logicDeleteColumnName("deleted")
                        .enableTableFieldAnnotation()
                        .controllerBuilder()
                        // 映射路径使用连字符格式，而不是驼峰
                        .enableHyphenStyle()
                        .formatFileName("%sController")
                        .enableRestStyle()
                        .mapperBuilder()
                        // 生成通用的resultMap
                        .enableBaseResultMap()
                        .superClass(BaseMapper.class)
                        .formatMapperFileName("%sMapper")
                        .enableMapperAnnotation()
                        .formatXmlFileName("%sMapper")
                        // 开启覆盖
                        //mapperBuilder().fileOverride()
                        //.serviceBuilder().fileOverride()
                        //.controllerBuilder().fileOverride()
                        .entityBuilder().fileOverride())
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateConfig(builder -> builder
                        // 关闭生成xml和service
                        .disable(TemplateType.XML)
                        .disable(TemplateType.SERVICE))
                .execute();

    }

    @Test
    void test(){

    }

}
