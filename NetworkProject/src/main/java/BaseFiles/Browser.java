package BaseFiles;

public class Browser {

    public static BaseBrowser prepareBrowser() {
        String _browser = PropertiesFileReader.getDataInPropertiesFile("browserName").toLowerCase();
        byte b_Value = -5;

        if(_browser.equalsIgnoreCase("chrome")){
            b_Value=0;
        }else if (_browser.equalsIgnoreCase("firefox")){
            b_Value=1;
        }else if (_browser.equalsIgnoreCase("ie")){
            b_Value=2;
        }else{
            b_Value=6;
        }

        switch(b_Value) {
            case 0:
                return new Chrome();
            case 1:
                return new Firefox();
            case 2:
                // return new IE();
            case 3:
            default:
                return new Chrome();
        }


    }

}
