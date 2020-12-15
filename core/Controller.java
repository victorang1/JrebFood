package core;

import util.StringUtil;

public abstract class Controller {

    protected String errorMessage;

    public String getErrorMessage() {
        if (StringUtil.isNullOrEmpty(errorMessage)) return "Error Occurred!";
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
