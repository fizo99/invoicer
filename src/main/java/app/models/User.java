package main.java.app.models;

public class User{
    private String fullName;
    private String fullAddress;
    private String NIP;
    private String phoneNumber;
    private String email;
    private String BDO;
    private String GTU;
    private String accountNumber;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBDO() {
        return BDO;
    }

    public void setBDO(String BDO) {
        this.BDO = BDO;
    }

    public String getGTU() {
        return GTU;
    }

    public void setGTU(String GTU) {
        this.GTU = GTU;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static class Builder {
        private String fullName;
        private String fullAddress;
        private String NIP;
        private String phoneNumber;
        private String email;
        private String BDO;
        private String GTU;
        private String accountNumber;

        public Builder fullName(String fullName){
            this.fullName = fullName;
            return this;
        }

        public Builder fullAddress(String fullAddress){
            this.fullAddress = fullAddress;
            return this;
        }

        public Builder NIP(String NIP){
            this.NIP = NIP;
            return this;
        }

        public Builder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder email(String email){
            this.email = email;
            return this;
        }
        public Builder BDO(String BDO){
            this.BDO = BDO;
            return this;
        }
        public Builder GTU(String GTU){
            this.GTU = GTU;
            return this;
        }
        public Builder accountNumber(String accountNumber){
            this.accountNumber = accountNumber;
            return this;
        }
        public User build(){
            User user = new User();
            user.fullName = this.fullName;
            user.fullAddress = this.fullAddress;
            user.NIP = this.NIP;
            user.phoneNumber = this.phoneNumber;
            user.email = this.email;
            user.BDO = this.BDO;
            user.GTU = this.GTU;
            user.accountNumber = this.accountNumber;
            return user;
        }
    }
}
