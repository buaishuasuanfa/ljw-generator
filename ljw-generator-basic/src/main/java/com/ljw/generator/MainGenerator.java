package com.ljw.generator;

import com.ljw.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生产器（动静结合）
 */
public class MainGenerator {

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("yupi");
        mainTemplateConfig.setLoop(true);

        doGenerator(mainTemplateConfig);
    }

    private static void doGenerator(MainTemplateConfig mainTemplateConfig) throws TemplateException, IOException {
        // 整个项目的根路径
        String projectPath = System.getProperty("user.dir");
        // 目标路径
        String targetPath = projectPath+File.separator+"ljw-generator-basic";

        // 输入/出路径
        String inputPath = projectPath+File.separator+"ljw-generator-demo-projects/acm-template";
        String outputPath = targetPath;

        //生产静态文件
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);

        // 生产动态文件
        String inputDynamicPath = targetPath+File.separator+"src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicPath = targetPath+File.separator+"acm-template/src/com/yupi/acm/MainTemplate.java";
        DynamicGenerator.doGenerate(inputDynamicPath,outputDynamicPath,mainTemplateConfig);

    }
}
