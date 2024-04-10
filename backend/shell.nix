let
    pkgs = import <nixpkgs> {};
in pkgs.mkShell {
    JAVA_HOME = pkgs.jdk17.home;
    JAVA_LATEST_HOME = pkgs.jdk21.home;

    packages = [
        pkgs.jdk17
        pkgs.jdk21

        pkgs.gradle
    ];
}
