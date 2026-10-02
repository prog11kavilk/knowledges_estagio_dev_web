#!/bin/sh
# Instala o mitraseeks.service como serviço do seu usuário: o back (e o front) sobem sozinhos no login.
# Uso: sh instalar-servico.sh            instala, habilita e inicia
#      sh instalar-servico.sh remover    para e remove
set -e
PASTA="$(cd "$(dirname "$0")" && pwd)"
DESTINO="$HOME/.config/systemd/user/mitraseeks.service"

if [ "$1" = "remover" ]; then
    systemctl --user disable --now mitraseeks.service 2>/dev/null || true
    rm -f "$DESTINO"
    systemctl --user daemon-reload
    echo "Serviço removido."
    exit 0
fi

mkdir -p "$(dirname "$DESTINO")"
sed "s|__PASTA_DO_BACK__|$PASTA|" "$PASTA/mitraseeks.service" > "$DESTINO"
systemctl --user daemon-reload
systemctl --user enable --now mitraseeks.service
echo "Pronto! Abra http://localhost:8080 (o primeiro start leva uns 20 s)."
echo "Log: journalctl --user -u mitraseeks -f"
