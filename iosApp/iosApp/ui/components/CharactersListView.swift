//
//  CharactersListView.swift
//  iosApp
//
//  Created by Daniel Ávila on 26/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CharactersListView: View {
    private let characters: [Character]
    
    init(characters: [Character]){
        self.characters = characters
    }
    
    var body: some View {
        List {
            ForEach(characters, id: \.self) { character in
                NavigationLink(destination: CharacterDetailView(characterId: Int(character.id))) {
                    ItemCharacterView(character: character)
                }
            }
        }
    }
}
