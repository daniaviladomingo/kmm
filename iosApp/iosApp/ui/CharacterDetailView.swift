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
    
    var characterId: Int
    
    @ObservedObject var viewModel: CharacterDetailVM = CharacterDetailVM()
    
    init(characterId: Int) {
        self.characterId = characterId
    }

    var body: some View {
        VStack {
            Text(viewModel.character.name)
                .font(.title)
                .bold()
            AsyncImage(url: URL(string: viewModel.character.image))
            { image in
                image
                    .cornerRadius(20)
            } placeholder: {
                ProgressView().frame(width: 100, height: 100, alignment: .center)
            }
            
            Text(viewModel.character.location)
                .padding(.top, 5)
            
            switch viewModel.character.status {
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
                viewModel.setEvent(event: CharacterDetailContractEvent.RemoveCharacterToFavorite.shared)
            } else {
                viewModel.setEvent(event: CharacterDetailContractEvent.AddCharacterToFavorite.shared)
            }
        }, label: {
            Image(systemName: viewModel.isFavorite ? "star.fill" : "star")
        }))
        .alert(isPresented: $viewModel.showAlert, content: {
            return Alert(title: Text(viewModel.isFavorite ? "Character added to favorite" : "Character removed to favorite"))
        })
        .onAppear(perform: {
            viewModel.setEvent(event: CharacterDetailContractEvent.GetCharacter(idCharacter: Int32(characterId)))
        })
    }
}

struct CharacterDetailView_Previews: PreviewProvider {
    static var previews: some View {
        CharacterDetailView(characterId: 0)
    }
}
