package main.java.app.models;

public class Buyer {
    private String NIP;
    private String fullName;
    //private String fullAddress;
    private String town;
    private String street;
    private String streetNumber;
    private String ZIPCode;

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

//    public String getFullAddress() {
//        return fullAddress;
//    }
//
//    public void setFullAddress(String fullAddress) {
//        this.fullAddress = fullAddress;
//    }

    @Override
    public String toString() {
        return "Buyer{" +
                "NIP='" + NIP + '\'' +
                ", name='" + fullName + '\'';
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

    //private String surname;
    //private String companyName;

    //private String town;
    //private String streetName;
    //private String streetNumber;
    //private String ZIPcode;


    public static final class Builder {
        private String NIP;
        private String fullName;
//        private String fullAddress;
//        private String NIP = "";
//        private String name = "";
//        private String surname = "";
//        private String companyName = "";
        private String town = "";
        private String street = "";
        private String streetNumber = "";
        private String ZIPcode = "";

        public Builder NIP(String NIP) {
            this.NIP = NIP;
            return this;
        }

        public Builder fullName(String name) {
            this.fullName = name;
            return this;
        }

        public Builder town(String town) {
            this.town = town;
            return this;
        }
        public Builder street(String street) {
            this.street = street;
            return this;
        }
        public Builder streetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }
        public Builder ZIPcode(String ZIPcode) {
            this.ZIPcode = ZIPcode;
            return this;
        }




        public Buyer build() {
            Buyer buyer = new Buyer();
            buyer.NIP = NIP;
            buyer.fullName = fullName;
            buyer.town = town;
            buyer.street = street;
            buyer.streetNumber = streetNumber;
            buyer.ZIPCode = ZIPcode;
            //buyer.fullAddress = fullAddress;
//            buyer.name = name;
//            buyer.surname = surname;
//            buyer.companyName = companyName;
//            buyer.town = town;
//            buyer.streetName = streetName;
//            buyer.streetNumber = streetNumber;
//            buyer.ZIPcode = ZIPcode;
            return buyer;
        }
    }
}
