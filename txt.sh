#!/bin/bash

# Script para extrair informações dos arquivos do mod Dragon Block Infinity
# - Java e JSON: imprime o conteúdo completo
# - PNG e OGG: apenas lista os caminhos
# Salva tudo em projeto.txt

OUTPUT_FILE="projeto.txt"

echo "=== ARQUIVOS JAVA ===" > "$OUTPUT_FILE"
find src -name "*.java" -type f | while read -r file; do
    echo "Arquivo: $file" >> "$OUTPUT_FILE"
    echo "Conteúdo:" >> "$OUTPUT_FILE"
    cat "$file" >> "$OUTPUT_FILE"
    echo "" >> "$OUTPUT_FILE"
    echo "----------------------------------------" >> "$OUTPUT_FILE"
done

echo "=== ARQUIVOS JSON ===" >> "$OUTPUT_FILE"
find src -name "*.json" -type f | while read -r file; do
    echo "Arquivo: $file" >> "$OUTPUT_FILE"
    echo "Conteúdo:" >> "$OUTPUT_FILE"
    cat "$file" >> "$OUTPUT_FILE"
    echo "" >> "$OUTPUT_FILE"
    echo "----------------------------------------" >> "$OUTPUT_FILE"
done

echo "=== ARQUIVOS PNG ===" >> "$OUTPUT_FILE"
find src -name "*.png" -type f | while read -r file; do
    echo "$file" >> "$OUTPUT_FILE"
done

echo "=== ARQUIVOS OGG ===" >> "$OUTPUT_FILE"
find src -name "*.ogg" -type f | while read -r file; do
    echo "$file" >> "$OUTPUT_FILE"
done

echo "Arquivo projeto.txt gerado com sucesso!"
echo "=== RESOLUÇÃO DAS IMAGENS ===" >> "$OUTPUT_FILE"
find src -name "*.png" -type f | while read -r file; do
    size=$(file "$file" | grep -oP '\d+ x \d+' | head -1)
    echo "$file: $size" >> "$OUTPUT_FILE"
done
