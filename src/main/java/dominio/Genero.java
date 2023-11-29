package dominio;

public enum Genero {
    MASCULINO((long) 1),
    FEMININO((long) 2),
    OUTRO((long) 3);

    private final Long id;

    Genero(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static Genero mapearEnum(Long id) {
        for (Genero genero : Genero.values()) {
            if (genero.getId() == id) {
                return genero;
            }
        }
        throw new IllegalArgumentException("ID de gênero inválido: " + id);
    }
}
