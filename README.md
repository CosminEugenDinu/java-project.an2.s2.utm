# Install

https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-on-ubuntu-20-04

```bash
sudo apt update
```

```bash
sudo apt install default-jre
```

```bash
java -version
```

```bash
sudo apt install default-jdk
```

```bash
javac -version
```

# Or

```bash
sudo apt install openjdk-13-jre-headless
```

```bash
sudo apt install openjdk-13-jdk-headless
```

## Development

### Install development prerequisited

- Install [nvm](https://github.com/nvm-sh/nvm)

```bash
wget -qO- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash
```

- Install node and npm

```bash
nvm install node
```

- Install nodemon globally (used for src file watch and compile)

```bash
npm install -g nodemon
```

### Build

```bash
./compile
```

### Start

```bash
./start
```

### Start - watch, compile, run

```bash
./start-dev
```
