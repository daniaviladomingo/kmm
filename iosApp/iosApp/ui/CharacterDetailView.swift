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
    
    var character: Character
    
    @ObservedObject var viewModel: CharacterDetailVM = CharacterDetailVM()
    
    init(character: Character) {
        self.character = character
    }

    var body: some View {
        VStack {
            Text(character.name)
                .font(.title)
                .bold()
            AsyncImage(url: URL(string: character.image))
            { image in
                image
                    .cornerRadius(20)
            } placeholder: {
                ProgressView().frame(width: 100, height: 100, alignment: .center)
            }
            
            Text(character.location)
                .padding(.top, 5)
            
            switch character.status {
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
        .navigationBarItems(trailing: Button(action: {
            if (viewModel.isFavorite) {
                viewModel.setEvent(event: CharacterDetailContractEvent.RemoveCharacterToFavorite)
            } else {
                viewModel.setEvent(event: CharacterDetailContractEvent.AddCharacterToFavorite)
            }
        }, label: {
            Image(systemName: viewModel.isFavorite ? "star.fill" : "star")
        }))
        .alert(isPresented: $viewModel.showAlert, content: {
            return Alert(title: Text(viewModel.isFavorite ? "Character added to favorite" : "Character removed to favorite"))
        })
        .onAppear(perform: {
            viewModel.setEvent(event: CharacterDetailContractEvent.CheckIfIsFavorite(idCharacter: character.id))
        })
    }
}

struct CharacterDetailView_Previews: PreviewProvider {
    static var previews: some View {
        CharacterDetailView(character: Character(id: 0, name: "Daniel", status: Status.alive, species: "Human", gender: Gender.male, origin: "Belmonte de Tajo", location: "Iceland", image: ""))
    }
}
