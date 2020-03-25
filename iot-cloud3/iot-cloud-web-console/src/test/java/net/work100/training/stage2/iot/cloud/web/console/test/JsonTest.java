package net.work100.training.stage2.iot.cloud.web.console.test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.work100.training.stage2.iot.cloud.commons.utils.MapperUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: JsonTest</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-22 17:44
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-22   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class JsonTest {
    public static void main(String[] args) {
        serializationAndDeserialization();
        serializationAndDeserializationForList();
    }

    private static void serializationAndDeserialization() {
        try {
            String jsonString = "{\"name\":\"张三\", \"age\":21}";
            // 反序列化 JSON 到对象
            Student student = MapperUtils.json2pojo(jsonString, Student.class);
            System.out.println(student);
            System.out.println("------------------------------");

            // 序列化对象到 JSON
            String json = MapperUtils.obj2json(student);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void serializationAndDeserializationForList() {
        try {
            String jsonString = "{\"draw\":1,\"recordsTotal\":1,\"recordsFiltered\":1,\"data\":[{\"name\":\"张三\", \"age\":21},{\"name\":\"李四\", \"age\":25}],\"error\":null}";

            // 反序列化 JSON 到集合
            List<Student> students = MapperUtils.json2list(jsonString, "data", Student.class);
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println("------------------------------");

            // 序列化集合到 JSON
            String json = MapperUtils.obj2json(students);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Student {
    private String name;
    private int age;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Student [ name: " + name + ", age: " + age + " ]";
    }
}
