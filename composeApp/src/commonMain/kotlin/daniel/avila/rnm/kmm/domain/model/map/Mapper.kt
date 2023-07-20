package daniel.avila.rnm.kmm.domain.model.map

abstract class Mapper<M, P> {
    abstract fun map(model: M): P
    abstract fun inverseMap(model: P): M

    fun map(values: List<M>): List<P> = values.map { map(it) }
    fun inverseMap(values: List<P>): List<M> = values.map { inverseMap(it) }
}