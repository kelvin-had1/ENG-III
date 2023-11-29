package dominio;

public enum TipoEndereco {
	Entrega((long) 1),
    Cobranca((long) 2),
	Residencial((long) 3);
    
    private final Long id;

    TipoEndereco(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static TipoEndereco mapearEnum(Long id) {
        for (TipoEndereco tipo : TipoEndereco.values()) {
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("ID de tipo inválido: " + id);
    }
}
