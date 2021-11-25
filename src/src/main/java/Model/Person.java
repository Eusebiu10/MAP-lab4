package Model;

public class Person {
    private String firstName;
    private String lastName;



    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }


    public Person(){
        this.firstName="";
        this.lastName="";
    }


    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public String getFirstName() {
        return firstName;
    }



    public String getLastName() {
        return lastName;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


       public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}