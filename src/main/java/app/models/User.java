package main.java.app.models;

public class User{
    private String fullName;
    //private String fullAddress;
    private String town;
    private String street;
    private String streetNumber;
    private String ZIPCode;
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

    //public String getFullAddress() {
    //        return fullAddress;
    //}

    //public void setFullAddress(String fullAddress) {
    //    this.fullAddress = fullAddress;
    //}

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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZIPCode() {
        return ZIPCode;
    }

    public void setZIPCode(String ZIPCode) {
        this.ZIPCode = ZIPCode;
    }

    public static class Builder {
        private String fullName;
        //private String fullAddress;
        private String town;
        private String street;
        private String streetNumber;
        private String ZIPCode;
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
        public Builder town(String town){
            this.town = town;
            return this;
        }
        public Builder street(String street){
            this.street = street;
            return this;
        }
        public Builder streetNumber(String streetNumber){
            this.streetNumber = streetNumber;
            return this;
        }
        public Builder ZIPCode(String ZIPCode){
            this.ZIPCode = ZIPCode;
            return this;
        }

//        public Builder fullAddress(String fullAddress){
//            this.fullAddress = fullAddress;
//            return this;
//        }

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
            user.town = this.town;
            user.street = this.street;
            user.streetNumber = this.streetNumber;
            user.ZIPCode = this.ZIPCode;
            //user.fullAddress = this.fullAddress;
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
