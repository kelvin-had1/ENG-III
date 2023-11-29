package dominio;

public enum TipoTelefone {
    Residencial((long) 1),
    Celular((long) 2);
    
    private final Long id;

    TipoTelefone(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static TipoTelefone mapearEnum(Long id) {
        for (TipoTelefone tipo : TipoTelefone.values()) {
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("ID de tipo inválido: " + id);
    }
}

