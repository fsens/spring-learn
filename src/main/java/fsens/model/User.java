package fsens.model;

public class User {
    private String name;

    private String sex;

    private String age;

    public User(String name, String sex, String age){
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName(){
        return this.name;
    }

    public String getSex(){
        return this.sex;
    }

    public String getAge(){
        return this.age;
    }


}
