package login.domain;


//Ansvarlig: Patrick
public class SubTaskRoleViewModel {
    private String RoleDescription;
    private int Hours;
    private double Price;
    private double FinalPrice;

    public SubTaskRoleViewModel(String roleDescription, int hours, double price, double finalPrice) {
        RoleDescription = roleDescription;
        Hours = hours;
        Price = price;
        FinalPrice = finalPrice;
    }

    public String getRoleDescription() {
        return RoleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        RoleDescription = roleDescription;
    }

    public int getHours() {
        return Hours;
    }

    public void setHours(int hours) {
        Hours = hours;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getFinalPrice() {
        return FinalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        FinalPrice = finalPrice;
    }

    @Override
    public String toString() {
        return "SubTaskRoleViewModel{" +
                "RoleDescription='" + RoleDescription + '\'' +
                ", Hours=" + Hours +
                ", Price=" + Price +
                ", FinalPrice=" + FinalPrice +
                '}';
    }
}
