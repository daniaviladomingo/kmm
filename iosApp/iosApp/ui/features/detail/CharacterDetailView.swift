//
//  CharacterView.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 15/9/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CharacterDetailView: View {
        
    @ObservedObject var viewModel: CharacterDetailVM = CharacterDetailVM()
    
    init(characterId: Int) {
        viewModel.setEvent(event: CharacterDetailContractEventGetCharacter.init(idCharacter: Int32(characterId)))
    }

    var body: some View {
        ManagementResourceState(
            resourceState: viewModel.state.character,
            successView: { character in
                CharacterView(character: character!)
            },
            onTryAgain: { viewModel.setEvent(event: CharacterDetailContractEventRetry.shared) },
            onCheckAgain: { viewModel.setEvent(event: CharacterDetailContractEventRetry.shared) }
        )
        .navigationBarItems(trailing: Button(action: {
            if (viewModel.state.isFavorite) {
                viewModel.setEvent(event: CharacterDetailContractEventRemoveCharacterToFavorite.shared)
            } else {
                viewModel.setEvent(event: CharacterDetailContractEventAddCharacterToFavorite.shared)
            }
        }, label: {
            Image(systemName: viewModel.state.isFavorite ? "star.fill" : "star")
        }))
        .alert(isPresented: $viewModel.showAlert, content: {
            return Alert(title: Text(viewModel.state.isFavorite ? "Character added to favorite" : "Character removed to favorite"))
            })
    }
}

struct CharacterView: View {
    
    private let character: Character
    
    init(character: Character) {
        self.character = character
    }
    
    var body: some View {
        VStack {
            Text(self.character.name)
                .font(.title)
                .bold()
            AsyncImage(url: URL(string: self.character.image))
            { image in
                image
                    .cornerRadius(20)
            } placeholder: {
                ProgressView().frame(width: 100, height: 100, alignment: .center)
            }
            
            Text(self.character.location)
                .padding(.top, 5)
            
            switch self.character.status {
            case Status.alive:
                Text("Alive")
                    .foregroundColor(.green)
                    .padding(.top, 5)
            case Status.dead:
                Text("Dead")
                    .foregroundColor(.red)
                    .padding(.top, 5)
            case Status.unknown:
                Text("Unknown")
                    .foregroundColor(.yellow)
                    .padding(.top, 5)
            default:
                Text("Unknown")
                    .foregroundColor(.yellow)
                    .padding(.top, 5)
            }
        }
    }
}

struct CharacterDetailView_Previews: PreviewProvider {
    static var previews: some View {
        CharacterDetailView(characterId: 0)
    }
}
