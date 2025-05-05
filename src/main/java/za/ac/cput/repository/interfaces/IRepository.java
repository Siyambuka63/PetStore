package za.ac.cput.repository.interfaces;

public interface IRepository<Type, id> {
    Type create(Type type);

    Type read(id id);

    Type update(Type type);

    Type delete(id id);
}
