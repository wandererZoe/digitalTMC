package com.digitalTMC.service.vo;

import com.digitalTMC.util.enums.Pathway;
import com.digitalTMC.util.enums.PathwayLevel;

public class MemberPathwayVO {
    private Pathway pathway;
    private PathwayLevel pathwayLevel;
    private boolean isCurrent;

    public MemberPathwayVO(Pathway pathway, PathwayLevel pathwayLevel, boolean isCurrent) {
        this.pathway = pathway;
        this.pathwayLevel = pathwayLevel;
        this.isCurrent = isCurrent;
    }

    public MemberPathwayVO() {
    }

    public Pathway getPathway() {
        return pathway;
    }

    public void setPathway(Pathway pathway) {
        this.pathway = pathway;
    }

    public PathwayLevel getPathwayLevel() {
        return pathwayLevel;
    }

    public void setPathwayLevel(PathwayLevel pathwayLevel) {
        this.pathwayLevel = pathwayLevel;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }
}
