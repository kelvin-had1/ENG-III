package dominio;

public enum BandeiraSeguranca {
	Mastercard((long) 1),
    Visa((long) 2);

    private final Long id;

    BandeiraSeguranca(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static BandeiraSeguranca mapearEnum(Long id) {
        for (BandeiraSeguranca bandeira : BandeiraSeguranca.values()) {
            if (bandeira.getId().equals(id)) {
                return bandeira;
            }
        }
        throw new IllegalArgumentException("ID de bandeira inválida: " + id);
    }
}
