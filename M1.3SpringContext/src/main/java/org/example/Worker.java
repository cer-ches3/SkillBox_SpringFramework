package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Worker {
    //@Autowired
    //@Qualifier("simpleLogicClass")
    private final LogicClass logicClass;

    //@Autowired
    //@Qualifier("simpleLogicData")
    private final LogicClass dataSimpleLogic;

    private final DataComponent dataComponent;

//    @Autowired
//    public void setDataComponent(DataComponent dataComponent) {
//        this.dataComponent = dataComponent;
//    }


    @Autowired
    public Worker(LogicClass simpleLogicClass, LogicClass simpleLogicData, DataComponent dataComponent) {
        this.logicClass = simpleLogicClass;
        this.dataSimpleLogic = simpleLogicData;
        this.dataComponent = dataComponent;
    }

    public void call() {
        logicClass.simpleLogic();
        dataSimpleLogic.printLogicData();
        dataComponent.someWork();
    }
}
