//
//  CharactersViewController.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 01/01/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit
import shared

class CharactersViewController: BaseViewController<CharactersPresenter>, ICharactersView, INavigatorCharacters {
    func navigateToDetail(character: Character) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        
        let characterDetailPresenter = CharacterDetailPresenter(
            isCharacterFavoriteUseCase: IsCharacterFavoriteUseCase(repository: appDelegate.repository),
            addCharacterToFavoritesUseCase: AddCharacterToFavoritesUseCase(repository: appDelegate.repository),
            removeCharacterFromFavoritesUseCase: RemoveCharacterFromFavoritesUseCase(repository: appDelegate.repository),
            executor: DiKt.executor
        )
        
        let characterDetailViewController = CharacterDetailViewController()
        characterDetailViewController.presenter = characterDetailPresenter
        characterDetailViewController.character = character
              
        appDelegate.nvc.pushViewController(characterDetailViewController, animated: true)
//        appDelegate.nvc.viewControllers = [characterDetailViewController]
    }
    
    @objc func navigateToFavorites() {
        
    }
    
    private let tableView = UITableView()
    private var characters: [Character] = []
    
    override func viewDidLoad() {
        buildUI()
        presenter!.loadCharacters()
    }
    
    func displayCharacters(characters: [Character]) {
        self.characters = characters
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
        
        navigationItem.title = "Kotlin Multiplatform Mobile"
        
        navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .organize, target: self, action: #selector(navigateToFavorites))
 
    }
}

extension CharactersViewController: UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return characters.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let character = characters[indexPath.row]
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath) as! CharacterViewCell
        
        cell.character = character
        
//        if indexPath.row == characters.count-1 {
//            presenter?.loadCharacters()
//        }
                
        return cell
    }
}

extension CharactersViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        presenter?.onCharacterClick(character: characters[indexPath.row])
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100
    }
}

