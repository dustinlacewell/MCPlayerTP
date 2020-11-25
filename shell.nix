let
  pkgs = import <nixpkgs> {};
in
with pkgs; pkgs.mkShell {
  buildInputs = [
    gradle
    adoptopenjdk-bin
    jetbrains.idea-community
  ];
}
