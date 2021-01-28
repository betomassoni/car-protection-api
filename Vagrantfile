# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|

  config.vm.define "car_protection" do |config|
    config.vm.provider :digital_ocean do |provider, override|
      override.ssh.private_key_path = '~/.ssh/digitalocean_rsa'        
      override.vm.box = 'digital_ocean'
      override.vm.box_url = "https://github.com/devopsgroup-io/vagrant-digitalocean/raw/master/box/digital_ocean.box"
      override.nfs.functional = false
      override.vm.allowed_synced_folder_types = :rsync
      provider.token = [YOUR_TOKEN_HERE]
      provider.image = 'ubuntu-18-04-x64'
      provider.region = 'nyc1'
      provider.size = 's-1vcpu-1gb' 
      provider.backups_enabled = false
      provider.private_networking = false
      provider.ipv6 = false
      provider.monitoring = false
      provider.ssh_key_name = 'digitalocean_rsa' 
    end

    config.vm.provision "shell", 
          inline: "apt-get update && apt-get install -y docker.io"  

    config.vm.provision "shell",
          inline: "curl -L https://github.com/docker/compose/releases/download/1.15.0/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose && \
                   chmod +x /usr/local/bin/docker-compose"
    
    config.vm.synced_folder "./docker", "/docker"
    config.vm.synced_folder ".", "/vagrant", disabled: true               
  end

end


