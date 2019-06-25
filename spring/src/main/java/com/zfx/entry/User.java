package com.zfx.entry;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

	@NotNull(message = "{isNoll}")
	@Size(min = 5 ,max = 10 ,message = "{size}")
    private String name ;
	
	@NotNull(message = "{isNoll}")
	@Size(min = 5 ,max = 10 ,message = "{size}")
	
    private String password ;
	
    private String age ;
    
    private String birthday ;

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAge() {
        return age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }
    
    public String getBirthday() {
        return birthday;
    }
    
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }
    
}
