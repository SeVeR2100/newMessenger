package requestLogic;

public class LogicFactory {

    public static ILogic getImpl(String request) {
        if (request!=null) {
            switch (request) {
                case "New_Acc":
                    return NewAccount.getInstance();
                case "Check_Acc":
                    return CheckAccount.getInstance();
                default:
                    throw new NullPointerException();
            }
        } else{
            throw new NullPointerException("Клиент закрывает соединение");
        }
    }
}