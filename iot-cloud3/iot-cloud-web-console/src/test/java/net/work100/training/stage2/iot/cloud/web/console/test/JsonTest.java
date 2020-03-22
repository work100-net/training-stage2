package net.work100.training.stage2.iot.cloud.web.console.test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
//        serializationAndDeserialization();
        serializationAndDeserializationForList();
    }

    private static void serializationAndDeserialization() {
        // 创建 ObjectMapper 对象
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"张三\", \"age\":21}";

        try {
            // 反序列化 JSON 到对象
            Student student = mapper.readValue(jsonString, Student.class);
            System.out.println(student);
            System.out.println("------------------------------");

            // 序列化对象到 JSON
            String json = mapper.writeValueAsString(student);
            System.out.println(json);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void serializationAndDeserializationForList() {
        // 创建 ObjectMapper 对象
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"draw\":1,\"recordsTotal\":1,\"recordsFiltered\":1,\"data\":[{\"name\":\"张三\", \"age\":21},{\"name\":\"李四\", \"age\":25}],\"error\":null}";

        try {
            // 反序列化 JSON 到树
            JsonNode jsonNode = mapper.readTree(jsonString);

            // 从树中读取 data 节点
            JsonNode jsonData = jsonNode.findPath("data");
            System.out.println(jsonData);
            System.out.println("------------------------------");

            // 反序列化 JSON 到集合
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Student.class);
            List<Student> students = mapper.readValue(jsonData.toString(), javaType);
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println("------------------------------");

            // 序列化集合到 JSON
            String json = mapper.writeValueAsString(students);
            System.out.println(json);
        } catch (IOException e) {
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
