//
//  CharacterDetailViewController.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 05/01/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit
import shared

class CharacterDetailViewController: BaseViewController<CharacterDetailPresenter>, ICharacterDetailView {
    var character: Character!
        
    let imageCharacter: UIImageView = {
        let img = UIImageView()
        img.contentMode = .scaleAspectFill
        img.translatesAutoresizingMaskIntoConstraints = false
        img.layer.cornerRadius = 35
        img.clipsToBounds = true
        return img
    }()
    
    let name: UILabel = {
        let label = UILabel()
        label.font = UIFont.boldSystemFont(ofSize: 20)
        label.textColor = .black
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    let species: UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 15)
        label.textColor = .gray
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    let status: UILabel = {
        let label = UILabel()
        label.font = UIFont.boldSystemFont(ofSize: 15)
        label.textColor = .gray
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
            
    override func viewDidLoad() {
        super.viewDidLoad()
        
        buildUI()
        presenter?.isFavorite(idCharacter: character.id)
        
        showCharacter(character: character)
        
        navigationItem.leftBarButtonItem = UIBarButtonItem(barButtonSystemItem: .done, target: self, action: #selector(done))
    }
    
    private func showCharacter(character: Character) {
        name.text = character.name
        species.text = "\(character.origin), \(character.species)"
        status.text = character.status.name
        
        switch character.status {
        case .alive:
            status.textColor = .green
        case .dead:
            status.textColor = .red
        case .unknown:
            status.textColor = .orange
        default:
            status.textColor = .orange
        }
        
        imageCharacter.load(url: URL(string: character.image)!)
    }
    
    private func buildUI() {
        view.backgroundColor = .white
        
        view.addSubview(imageCharacter)
        view.addSubview(name)
        view.addSubview(species)
        view.addSubview(status)
        
        NSLayoutConstraint.activate([
            imageCharacter.centerYAnchor.constraint(equalTo: view.safeAreaLayoutGuide.centerYAnchor, constant: -20),
            imageCharacter.centerXAnchor.constraint(equalTo: view.safeAreaLayoutGuide.centerXAnchor),
            imageCharacter.widthAnchor.constraint(equalToConstant: 200),
            imageCharacter.heightAnchor.constraint(equalToConstant: 200)
        ])
        
        NSLayoutConstraint.activate([
            name.bottomAnchor.constraint(equalTo: imageCharacter.topAnchor, constant: -20),
            name.centerXAnchor.constraint(equalTo: imageCharacter.centerXAnchor)
        ])
        
        NSLayoutConstraint.activate([
            species.topAnchor.constraint(equalTo: imageCharacter.bottomAnchor, constant: 20),
            species.centerXAnchor.constraint(equalTo: imageCharacter.centerXAnchor)
        ])
        
        NSLayoutConstraint.activate([
            status.topAnchor.constraint(equalTo: species.bottomAnchor, constant: 20),
            status.centerXAnchor.constraint(equalTo: imageCharacter.centerXAnchor)
        ])
    }
    
    func addedFavorite() {
        navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .trash, target: self, action: #selector(removeFavorite))
    }
    
    func removedFavorite() {
        navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .save, target: self, action: #selector(addFavorite))
    }
    
    func isFavorite(isFavorite: Bool) {
        if (isFavorite) {
            navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .trash, target: self, action: #selector(removeFavorite))
        } else {
            navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .save, target: self, action: #selector(addFavorite))
        }
    }
    
    @objc private func done() {
        dismiss(animated: false)
    }
    
    @objc private func removeFavorite() {
        presenter?.removeCharacterFromFavorite(id: character.id)
    }
    
    @objc private func addFavorite() {
        presenter?.addCharacterToFavorite(character: character)
    }
}
