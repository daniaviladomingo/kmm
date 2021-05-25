//
//  CharactersFavoritesViewController.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 06/01/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit
import shared

class CharacterFavoritesViewController: BaseViewController<CharactersFavoritesPresenter>, ICharactersFavoritesView, INavigatorCharactersFavorites {
    func navigateToDetail(character: Character) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
                
        let characterDetailViewController = CharacterDetailViewController()
        characterDetailViewController.presenter = CharacterDetailPresenter()
        characterDetailViewController.character = character
              
        appDelegate.nvc.pushViewController(characterDetailViewController, animated: true)
    }
    
    private let tableView = UITableView()
    private var characters: [Character] = []
    
    override func viewDidLoad() {
        buildUI()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        presenter!.loadCharacters()
    }
    
    func displayCharacters(characters: [Character]) {
        self.characters = characters
        characters.forEach { character in
            print(character)
        }
        tableView.reloadData()
    }
    
    private func buildUI() {
        view.backgroundColor = .white
        definesPresentationContext = true
        
        tableView.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(tableView)
        tableView.leadingAnchor.constraint(equalTo: view.leadingAnchor).isActive = true
        tableView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor).isActive = true
        tableView.trailingAnchor.constraint(equalTo: view.trailingAnchor).isActive = true
        tableView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor).isActive = true
        tableView.register(CharacterViewCell.self, forCellReuseIdentifier: "Cell")
        tableView.dataSource = self
        tableView.delegate = self
        
        navigationItem.title = "Favorites"
        
    }
}

extension CharacterFavoritesViewController: UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return characters.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let character = characters[indexPath.row]
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath) as! CharacterViewCell
        
        cell.character = character
                
        return cell
    }
}

extension CharacterFavoritesViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        presenter?.onCharacterClick(character: characters[indexPath.row])
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100
    }
}


