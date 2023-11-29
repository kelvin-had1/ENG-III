package dominio;

public enum TipoLogradouro {
    RUA((long) 1),
    AVENIDA((long) 2);

    private final Long id;

    TipoLogradouro(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static TipoLogradouro mapearEnum(Long id) {
        for (TipoLogradouro tipo : TipoLogradouro.values()) {
            if (tipo.getId().equals(id)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("ID de tipo de logradouro inválido: " + id);
    }
}
