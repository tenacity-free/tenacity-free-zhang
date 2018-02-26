package com.tenacity.free.project.manager.util;

import com.tenacity.free.project.manager.po.ProjectManagerDataType;
import com.tenacity.free.project.manager.po.ProjectManagerDataTypeField;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.FastDateFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.util
 * @file_name ProjectManagerDataTypeToCode.java
 * @description 代码生成工具
 * @create 2018-02-25 21:35
 */
public class ProjectManagerDataTypeToCode {

    /**
     * 根据DataType生成代码
     *
     * @param apiDataTypeDTO
     * @return
     */
    public static String parseDataTypeToCode(ProjectManagerDataType apiDataTypeDTO) {
        StringBuffer sb = new StringBuffer();

        // package
        //sb.append("package com.dianping.apihome.demo.response;\r\n");
        sb.append("\r\n");

        // import
        Set<String> importSet = new HashSet<String>();
        if (CollectionUtils.isNotEmpty(apiDataTypeDTO.getFieldList())) {
            for (ProjectManagerDataTypeField field: apiDataTypeDTO.getFieldList()) {
                String fieldTypeImportItem = field.getFieldDatatype().getName();

                if (fieldTypeImportItem!=null && fieldTypeImportItem.equalsIgnoreCase("date")) {
                    String importItem = "import java.util.Date;";
                    importSet.add(importItem);
                    sb.append(importItem + "\r\n");
                }
                if (field.getFieldType() == 1) {
                    String importItem = "import java.util.List;";
                    importSet.add(importItem);
                    sb.append(importItem + "\r\n");
                }
            }
            sb.append("\r\n");
        }

        // 类注释
        sb.append("/**\r\n");
        sb.append("*\t" + apiDataTypeDTO.getAbout()+ "\r\n");
        sb.append("*\r\n");
        sb.append("*\tCreated by ApiHome on "+ FastDateFormat.getInstance("yy/MM/dd").format(new Date()) +".\r\n");
        sb.append("*/ \r\n");

        // 实体部分
        sb.append("public class " + upFirst(apiDataTypeDTO.getName())  + "Response {");
        sb.append("\r\n\r\n");

        // field
        if (CollectionUtils.isNotEmpty(apiDataTypeDTO.getFieldList())) {
            for (ProjectManagerDataTypeField field: apiDataTypeDTO.getFieldList()) {
                String fieldTypeItem = matchJavaType(field.getFieldDatatype().getName());
                String fieldNameItem = field.getFieldName();
                if (field.getFieldType() == 1) {
                    fieldTypeItem = "List<"+ fieldTypeItem +">";
                }
                sb.append("\tprivate " + fieldTypeItem + " " + fieldNameItem + ";\r\n");

            }
            sb.append("\r\n");
        }

        // get set
        if (CollectionUtils.isNotEmpty(apiDataTypeDTO.getFieldList())) {
            for (ProjectManagerDataTypeField field: apiDataTypeDTO.getFieldList()) {
                String fieldTypeItem = matchJavaType(field.getFieldDatatype().getName());
                String fieldNameItem = field.getFieldName();
                String fieldNameItemUpFirst = upFirst(field.getFieldName());

                if (field.getFieldType() == 1) {
                    fieldTypeItem = "List<"+ fieldTypeItem +">";
                }

                sb.append("\tpublic void set" + fieldNameItemUpFirst + "(" + fieldTypeItem + " " + fieldNameItem + "){\r\n");
                sb.append("\t\tthis." + fieldNameItem + "=" + fieldNameItem + ";\r\n");
                sb.append("\t}\r\n");
                sb.append("\tpublic " + fieldTypeItem + " get" + fieldNameItemUpFirst + "(){\r\n");
                sb.append("\t\treturn this." + fieldNameItem + ";\r\n");
                sb.append("\t}\r\n");

            }
            sb.append("\r\n");
        }

        sb.append("}\r\n");

        return sb.toString();
    }


    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    private static String upFirst(String str) {

        char[] ch = str.toCharArray();
        if(ch[0] >= 'a' && ch[0] <= 'z'){
            ch[0] = (char)(ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 匹配数据类型
     *
     * @param paramDataType
     * @return
     */
    private static String matchJavaType(String paramDataType) {

        if(paramDataType.equalsIgnoreCase("string")){
            return "String";
        } else if(paramDataType.equalsIgnoreCase("boolean")){
            return "boolean";
        } else if(paramDataType.equalsIgnoreCase("short")){
            return "short";
        } else if(paramDataType.equalsIgnoreCase("int")){
            return "int";
        } else if(paramDataType.equalsIgnoreCase("long")){
            return "long";
        } else if(paramDataType.equalsIgnoreCase("float")){
            return "float";
        } else if(paramDataType.equalsIgnoreCase("double")){
            return "double";
        } else if(paramDataType.equalsIgnoreCase("date") || paramDataType.equalsIgnoreCase("datetime")) {
            return "Date";
        } else if(paramDataType.equalsIgnoreCase("byte")){
            return "byte";
        }

        return paramDataType;
    }

}
