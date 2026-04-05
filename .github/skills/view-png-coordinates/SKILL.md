---
name: view-png-coordinates
summary: Skill to inspect real PNG assets and extract image coordinates, size, and file metadata for use in mod assets.
description: |
  Use when you need to inspect `.png` image files directly in the workspace, identify their actual dimensions, and extract coordinate or size information for texture/model mapping.
  This skill is for asset review and metadata extraction, not for modifying or generating new artwork.
  It is useful when working with block/item models, UV mapping, and texture placement in Minecraft resource packs or mods.
---

# View PNG Coordinates Skill

This skill helps the agent:

- open and inspect `.png` image files in the workspace
- report the image width and height in pixels
- report the actual file path and filename
- identify any visible coordinate information or pixel-based alignment details
- support texture/UV mapping tasks for item and block assets

## Use when

- the user asks to check PNG files and confirm their actual dimensions
- the user wants to know if an item texture is a flat front PNG instead of a proper block model texture
- the user needs coordinates, UV size, or pixel dimensions for a texture asset

## Examples

- "Verifique este arquivo `.png` e me diga o tamanho x e y e a forma correta de usar no model item."
- "Me mostre as coordenadas ou dimensões desse sprite para ajustar o UV do bloco."
- "Preciso saber se este item PNG é só uma face ou um model 3D completo."

## Notes

- Prefer workspace file paths under `src/main/resources/assets/dragonblockinfinity/`.
- If the requested file is not present, explain what files are available instead.
- Do not invent pixel coordinates; only report actual metadata or visible details from the file.
