//
//  CharacterDetailViewController.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 05/01/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit

class CharacterDetailViewController: BaseViewController<> {
    var id: Int!
    var character: Character!
    
    var presenter: IPresenterCharacter?
    
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
        presenter?.loadCharacter(id: id)
    }
    
    func showCharacter(character: Character) {
        self.character = character
        
        name.text = character.name
        species.text = "\(character.origin), \(character.species)"
        status.text = character.status.rawValue
        
        switch character.status {
        case .ALIVE:
            status.textColor = .green
        case .DEAD:
            status.textColor = .red
        case .UNKNOWN:
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
    
    func isCharacterFavorite(isFavorite: Bool) {
        if (isFavorite) {
            navigationItem.rightBarButtonItem = nil
        } else {
            navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .save, target: self, action: #selector(addFavorite))
        }
    }
    
    @objc private func addFavorite() {
        presenter?.addToFavorite(character: character)
    }
}
