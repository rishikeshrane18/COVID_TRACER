package com.example.covidtracer;

public class StateData {
    private String stateName;
    private String activeCases;
    private String confirmedCases;
    private String recoveredCases;
    private String deathCases;

    public StateData() {
    }

    public StateData(String stateName, String confirmedCases, String activeCases, String recoveredCases, String deathCases){
        this.stateName = stateName;
        this.confirmedCases = confirmedCases;
        this.activeCases = activeCases;
        this.recoveredCases = recoveredCases;
        this.deathCases = deathCases;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    public String getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(String confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    public String getRecoveredCases() {
        return recoveredCases;
    }

    public void setRecoveredCases(String recoveredCases) {
        this.recoveredCases = recoveredCases;
    }

    public String getDeathCases() {
        return deathCases;
    }

    public void setDeathCases(String deathCases) {
        this.deathCases = deathCases;
    }
}
