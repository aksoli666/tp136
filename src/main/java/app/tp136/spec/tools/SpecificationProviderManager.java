package app.tp136.spec.tools;

public interface SpecificationProviderManager<T> {
    SpecificationProvider<T> getProvider(String key);
}
