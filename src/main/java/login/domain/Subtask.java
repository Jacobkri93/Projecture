package login.domain;

public class Subtask {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String task_name;
    private int hours;
    private Double cost;
    private String employees;


    public Subtask(String task_name, int hours, double cost, String employees) {
        this.task_name = task_name;
        this.hours = hours;
        this.cost = cost;
        this.employees = employees;
    }



    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }


}
