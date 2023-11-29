package dominio;

public enum TipoResidencia {
    CASA((long) 1),
    APARTAMENTO((long) 2);

    private final Long id;

    TipoResidencia(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public static TipoResidencia mapearEnum(Long id) {
        for (TipoResidencia tipo : TipoResidencia.values()) {
            if (tipo.getId().equals(id)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("ID de tipo de residência inválido: " + id);
    }
}
