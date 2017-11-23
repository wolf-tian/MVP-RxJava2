package cn.compal.wolf.test;

import com.google.gson.annotations.SerializedName;


/**
 * Created by wolf on 2017/11/17.
 */

public class InstructionBooksBean
{

    @SerializedName("moduleThreeXIconUrl")
    public String moduleThreeXIconUrl;
    @SerializedName("instructionBooksUrl")
    public String instructionBooksUrl;
    @SerializedName("sysModuleName")
    public String sysModuleName;
    @SerializedName("sysModuleId")
    public String sysModuleId;
    @SerializedName("moduleTwoXIconUrl")
    public String moduleTwoXIconUrl;

    public String getModuleThreeXIconUrl()
    {
        return moduleThreeXIconUrl;
    }

    public void setModuleThreeXIconUrl(String moduleThreeXIconUrl)
    {
        this.moduleThreeXIconUrl = moduleThreeXIconUrl;
    }

    public String getInstructionBooksUrl()
    {
        return instructionBooksUrl;
    }

    public void setInstructionBooksUrl(String instructionBooksUrl)
    {
        this.instructionBooksUrl = instructionBooksUrl;
    }

    public String getSysModuleName()
    {
        return sysModuleName;
    }

    public void setSysModuleName(String sysModuleName)
    {
        this.sysModuleName = sysModuleName;
    }

    public String getSysModuleId()
    {
        return sysModuleId;
    }

    public void setSysModuleId(String sysModuleId)
    {
        this.sysModuleId = sysModuleId;
    }

    public String getModuleTwoXIconUrl()
    {
        return moduleTwoXIconUrl;
    }

    public void setModuleTwoXIconUrl(String moduleTwoXIconUrl)
    {
        this.moduleTwoXIconUrl = moduleTwoXIconUrl;
    }
}
