package net.roadtoagility.dflow4k

class MyAggregateFactory: AggregateFactory<EntityTest, MyIdentity>{
    //,AggregateFactory<MyAggregate, EntityTest>

    override fun create(source: MyIdentity): EntityTest {
        if(source.isValid){
            return EntityTest.from("my name", source, VersionId.new())
        }
        return EntityTest.from("", MyIdentity.from(EntityId.from(0)), VersionId.empty())
    }

//    override fun create(source: EntityTest): MyAggregate {
//        if(source.isValid){
//            return MyAggregate(source)
//        }
//        return MyAggregate(EntityTest.from("", MyIdentity.from(EntityId.from(0)), VersionId.empty()))
//    }
}

//class MyAggregate: ObjectBasedAggregationRoot<EntityTest, MyIdentity>{
//
//    constructor(entity: EntityTest):super(){
//        if(entity.isValid){
//            apply(entity)
//        }
//
//        appendValidationResult(entity.failures())
//    }
//}