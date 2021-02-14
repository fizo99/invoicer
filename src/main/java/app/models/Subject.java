package main.java.app.models;

public class Subject {
    private String NIP;
    private String name;
    private String surname;
    private String companyName;
    private String town;
    private String streetName;
    private String streetNumber;
    private String ZIPcode;

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZIPcode() {
        return ZIPcode;
    }

    public void setZIPcode(String ZIPcode) {
        this.ZIPcode = ZIPcode;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "NIP='" + NIP + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", companyName='" + companyName + '\'' +
                ", town='" + town + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", ZIPcode='" + ZIPcode + '\'' +
                '}';
    }

    public static final class Builder {
        private String NIP = "";
        private String name = "";
        private String surname = "";
        private String companyName = "";
        private String town = "";
        private String streetName = "";
        private String streetNumber = "";
        private String ZIPcode = "";

        public Builder NIP(String NIP) {
            this.NIP = NIP;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder town(String town) {
            this.town = town;
            return this;
        }

        public Builder streetName(String streetName) {
            this.streetName = streetName;
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


        public Subject build() {
            Subject subject = new Subject();
            subject.NIP = NIP;
            subject.name = name;
            subject.surname = surname;
            subject.companyName = companyName;
            subject.town = town;
            subject.streetName = streetName;
            subject.streetNumber = streetNumber;
            subject.ZIPcode = ZIPcode;
            return subject;
        }
    }
}
