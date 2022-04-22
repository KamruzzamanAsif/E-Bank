package BankCards;

public class CreateCard {

    public static Card createCard(String cardType, String ownerName) {
        if(cardType.equals("Master")){
            System.out.println("A Master Card has been created.");
            return new MasterCard(ownerName);
        }
        else if(cardType.equals("Visa")){
            System.out.println("A Visa Card has been created.");
            return new VisaCard(ownerName);
        }
        else if(cardType.equals("Ncc")){
            System.out.println("A Ncc Card has been created.");
            return new NccCard(ownerName);
        }
        else
            return null;
    }
}