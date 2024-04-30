let
    pkgs = import <nixpkgs> {};
in with pkgs; mkShell {
    shellHook = ''
      zsh
    '';

    JAVA_HOME = jdk21.home;
    JAVA_LATEST_HOME = jdk21.home;

    packages = [
        jdk17
        jdk21

        gradle
    ];
}
