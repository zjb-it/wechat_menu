package test;

import com.alibaba.fastjson.JSONPath;

/**
 * Created by zjb on 17-11-24.
 */

public class http {

    public static void main(String[] args) {

        Entity entity = new Entity(123, new Object());

        // Assert.assertSame(entity.getValue(), JSONPath.eval(entity, "$.value"));
        // Assert.assertTrue(JSONPath.contains(entity, "$.value"));
        // Assert.assertTrue(JSONPath.containsValue(entity, "$.id", 123));
        // Assert.assertTrue(JSONPath.containsValue(entity, "$.value", entity.getValue()));
        // Assert.assertEquals(2, JSONPath.size(entity, "$"));
        // Assert.assertEquals(0, JSONPath.size(new Object[], "$"));
        System.out.println(JSONPath.eval(entity, "$.id"));
    }


}
class Entity {
    private Integer id;
    private String name;
    private Object value;

    public Entity() {}
    public Entity(Integer id, Object value) { this.id = id; this.value = value; }
    public Entity(Integer id, String name) { this.id = id; this.name = name; }
    public Entity(String name) { this.name = name; }

    public Integer getId() { return id; }
    public Object getValue() { return value; }
    public String getName() { return name; }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setValue(Object value) { this.value = value; }}