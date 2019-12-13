ssh-keygen -t rsa
eval "$(ssh-agent -s)"
ssh-add ~/.ssh/id_rsa

vi authorized_keys

