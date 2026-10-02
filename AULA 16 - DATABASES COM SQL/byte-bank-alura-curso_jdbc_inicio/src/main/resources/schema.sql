CREATE TABLE IF NOT EXISTS conta (
    numero INT PRIMARY KEY,
    saldo DECIMAL(15,2) NOT NULL DEFAULT 0,
    cliente_nome VARCHAR(150) NOT NULL,
    cliente_cpf VARCHAR(14) NOT NULL,
    cliente_email VARCHAR(150) NOT NULL,
    esta_ativa BOOLEAN NOT NULL DEFAULT TRUE
)
;
