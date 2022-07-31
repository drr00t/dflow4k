package net.roadtoagility.dflow4k

import net.roadtoagility.dflow4k.validations.BaseValidation

interface IChangeSet<out TChange>
{
    fun getChange():TChange
}

interface AggregateFactory<out TAggregate, in TCreateFrom>
        where TCreateFrom: BaseValidation {
    fun create(source: TCreateFrom): TAggregate
}

open class ObjectBasedAggregationRoot<TChange, TEntityId>:BaseValidation(),
    IChangeSet<TChange> where TChange: BaseEntity<TEntityId>
{
    lateinit var aggregateRootEntity: TChange

    protected fun apply(item:TChange)
    {
        this.aggregateRootEntity = item;
    }

    override fun getChange():TChange
    {
        return this.aggregateRootEntity;
    }
}